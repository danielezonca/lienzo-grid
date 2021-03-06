/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.grid.client.util;

import java.util.List;

import org.kie.grid.client.model.GridColumn;

/**
 * Utilities class
 */
public class ColumnIndexUtilities {

    /**
     * Finds the UI Column index corresponding to a Model Column index from the provided range of columns.
     * A "UI Column" is a Canvas element as rendered from left-to-right representing a Grid. Index 0 would
     * be the left most column, index N would be the right most column. A "Model Column index" is the index
     * a column relates to in the Row's data. Row data is not reordered when columns are reordered.
     * @param columns The range of columns to check. Cannot be null.
     * @param modelColumnIndex The index a model column represents in a row of data.
     * @return The UI Column index corresponding to model column index.
     */
    public static int findUiColumnIndex(final List<GridColumn<?>> columns,
                                        final int modelColumnIndex) {
        for (int uiColumnIndex = 0; uiColumnIndex < columns.size(); uiColumnIndex++) {
            final GridColumn<?> c = columns.get(uiColumnIndex);
            if (c.getIndex() == modelColumnIndex) {
                return uiColumnIndex;
            }
        }
        throw new IllegalStateException("Column was not found!");
    }
}
