/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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

package org.kie.grid.client.widget.grid.renderers.columns.impl;

import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.Node;
import com.ait.lienzo.client.core.shape.Text;
import com.ait.lienzo.test.LienzoMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.grid.client.widget.grid.renderers.columns.GridColumnRenderer;
import org.kie.grid.client.widget.grid.renderers.grids.GridRenderer;
import org.kie.grid.client.widget.grid.renderers.themes.GridRendererTheme;
import org.mockito.Mock;
import org.kie.grid.client.model.GridCell;
import org.kie.grid.client.model.GridCellValue;
import org.kie.grid.client.widget.context.GridBodyCellRenderContext;
import org.kie.grid.client.widget.grid.renderers.columns.GridColumnRenderer;
import org.kie.grid.client.widget.grid.renderers.grids.GridRenderer;
import org.kie.grid.client.widget.grid.renderers.themes.GridRendererTheme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;

@RunWith(LienzoMockitoTestRunner.class)
public abstract class BaseColumnRendererTest<T, R extends GridColumnRenderer<T>> {

    @Mock
    private GridCell<T> cell;

    @Mock
    private GridCellValue<T> cellValue;

    @Mock
    private GridBodyCellRenderContext context;

    @Mock
    private GridRenderer gridRenderer;

    @Mock
    private GridRendererTheme theme;

    @Mock
    private Text text;

    @Mock
    private Node textNode;

    private R renderer;

    @Before
    public void setup() {
        this.renderer = getRenderer();

        doReturn(gridRenderer).when(context).getRenderer();
        doReturn(theme).when(gridRenderer).getTheme();
        doReturn(text).when(theme).getBodyText();
        doReturn(textNode).when(text).asNode();
    }

    protected abstract R getRenderer();

    protected abstract T getValueToRender();

    @Test
    public void testNullCell() {
        assertNull(renderer.renderCell(null, context));
    }

    @Test
    public void testNullCellValue() {
        doReturn(null).when(cell).getValue();

        assertNull(renderer.renderCell(cell, context));
    }

    @Test
    public void testNullCellValueValue() {
        doReturn(cellValue).when(cell).getValue();
        doReturn(null).when(cellValue).getValue();

        assertNull(renderer.renderCell(cell, context));
    }

    @Test
    public void testRendering() {
        doReturn(cellValue).when(cell).getValue();
        doReturn(getValueToRender()).when(cellValue).getValue();

        final Group g = renderer.renderCell(cell, context);
        assertNotNull(g);

        assertEquals(1,
                     g.getChildNodes().size());
        assertEquals(text,
                     g.getChildNodes().get(0));
    }
}
