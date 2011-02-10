/**
 * Copyright 2010 CosmoCode GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.cosmocode.rendering;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.annotation.Nullable;

/**
 * {@link ValueRenderer} for {@link ResultSet}s.
 *
 * @since 1.3
 * @author Willi Schoenborn
 */
public enum ResultSetValueRenderer implements ValueRenderer<ResultSet> {

    INSTANCE;
    
    @Override
    public void render(@Nullable ResultSet results, Renderer r) throws RenderingException {
        if (results == null) {
            r.nullValue();
        } else {
            try {
                final ResultSetMetaData meta = results.getMetaData();
                final int columnCount = meta.getColumnCount();
                final String[] columns = new String[columnCount];
                int size = 0;
                
                for (int i = 1; i <= columnCount; i++) {
                    final String column = meta.getColumnName(i);
                    columns[i] = column;
                }
                
                r.map();
                r.key("rows").list();
                while (results.next()) {
                    r.map();
                    for (int i = 1; i <= columnCount; i++) {
                        final String key = columns[i];
                        final Object value = results.getObject(i);
                        r.key(key).value(value);
                    }
                    r.endMap();
                    size++;
                }
                r.endList();
                r.key("size").value(size);
                r.key("columns").value(columns);
                r.endMap();
            } catch (SQLException e) {
                throw new RenderingException(e);
            } finally {
                try {
                    results.close();
                } catch (SQLException e) {
                    throw new RenderingException(e); 
                }
            }
        }
    }
    
}
