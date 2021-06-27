package com.valeriotor.iWanderBackend.model.converter;

import com.valeriotor.iWanderBackend.model.dto.CoordinateDTO;
import com.valeriotor.iWanderBackend.util.ConversionUtil;
import org.dozer.CustomConverter;

import java.util.Arrays;
import java.util.List;

public class DayRouteConverter implements CustomConverter {

    @Override
    public Object convert(Object destination, Object source, Class<?> destClass, Class<?> sourceClass) {
        if(source == null)
            return null;
        if(sourceClass.getName().equals("[B")) {
            byte[] bytes = (byte[]) source;
            List<CoordinateDTO> coordinateDTOS = ConversionUtil.bytesToRoute(bytes);
            return coordinateDTOS.toArray(new CoordinateDTO[0]);
        } else if(sourceClass.getName().equals("[Lcom.valeriotor.iWanderBackend.model.dto.CoordinateDTO;")) {
            CoordinateDTO[] coords = (CoordinateDTO[]) source;
            List<CoordinateDTO> coordinateDTOS = Arrays.asList(coords);
            return ConversionUtil.routeToBytes(coordinateDTOS);
        }
        return null;
    }
}
