package com.thoughtworks.lifegame;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

/**
 * Cell Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Jun 3, 2017</pre>
 */
public class CellTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: query(Planet planet)
     */
    @Test
    public void testQuery() throws Exception {
        Planet planet = new Planet(5);
        planet.init(Planet.InitState.LStyle);
        assertEquals(0, planet.getCell(0, 0).query(planet));
        assertEquals(4, planet.getCell(2, 2).query(planet));
        assertEquals(2, planet.getCell(3, 4).query(planet));
        assertEquals(2, planet.getCell(4, 2).query(planet));
        assertEquals(1, planet.getCell(4, 4).query(planet));
        assertEquals(3, planet.getCell(2, 3).query(planet));
        assertEquals(2, planet.getCell(3, 2).query(planet));
        assertEquals(3, planet.getCell(2, 4).query(planet));
    }

    /**
     * Method: update(Planet planet)
     */
    @Test
    public void testUpdate() throws Exception {
        Planet planet = new Planet(5);
        planet.init(Planet.InitState.LStyle);
        assertFalse(planet.getCell(0, 0).update(planet));
        assertFalse(planet.getCell(2, 2).update(planet));
        assertFalse(planet.getCell(3, 4).update(planet));
        assertFalse(planet.getCell(4, 2).update(planet));
        assertFalse(planet.getCell(4, 4).update(planet));
        assertTrue(planet.getCell(2, 3).update(planet));
        assertTrue(planet.getCell(3, 2).update(planet));
        assertTrue(planet.getCell(2, 4).update(planet));

    }

} 
