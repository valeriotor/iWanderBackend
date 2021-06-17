package com.valeriotor.iWanderBackend.util;

import com.google.common.collect.Lists;
import com.valeriotor.iWanderBackend.model.dto.CoordinateDTO;

import java.io.*;
import java.util.List;

public class ConversionUtil {

    public static byte[] routeToBytes(List<CoordinateDTO> coords) {
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(bos)) {
            dos.writeInt(coords.size());
            for(CoordinateDTO coord : coords) {
                dos.writeDouble(coord.getLatitude());
                dos.writeDouble(coord.getLongitude());
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<CoordinateDTO> bytesToRoute(byte[] binary) {
        try(ByteArrayInputStream bis = new ByteArrayInputStream(binary);
            DataInputStream dis = new DataInputStream(bis)) {
            int size = dis.readInt();
            List<CoordinateDTO> coords = Lists.newArrayList();
            for (int i = 0; i < size; i++) {
                coords.add(new CoordinateDTO(dis.readDouble(), dis.readDouble()));
            }
            return coords;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
