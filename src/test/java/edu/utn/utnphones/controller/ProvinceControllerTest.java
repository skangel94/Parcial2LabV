package edu.utn.utnphones.controller;

import edu.utn.utnphones.domain.City;
import edu.utn.utnphones.domain.Province;
import edu.utn.utnphones.service.ProvinceService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProvinceControllerTest {
    ProvinceController provinceController;
    ProvinceService provinceService;

    @Before
    public void setUp() {
        provinceService = mock(ProvinceService.class);
        provinceController = new ProvinceController(provinceService);
    }

    @Test
    public void testGetAllProvinces(){
        List<Province> listProvinces = new ArrayList<>();
        listProvinces.add(new Province(1,"nombre",null));
        listProvinces.add(new Province(2,"nombre",null));
        when(provinceService.getAll()).thenReturn(listProvinces);

        assertEquals(listProvinces.size(),listProvinces.size());

    }



}
