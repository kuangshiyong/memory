package com.memory.common.utils;


import org.apache.commons.lang3.ArrayUtils;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class ByteUtils {

    private static final byte[] TO_SHORT = {0x00};

    private static final byte[] TO_INTEGER = {0x00, 0x00};

    private static final HexBinaryAdapter hexBinaryAdapter = new HexBinaryAdapter();

    /** Byte数组转为整型, 限制{0x00, 0x00} - {0xFF, 0XFF}
     * @param data：暂限定byte数组长度为1或2
     * @return, 如 0xFF => 255
     */
    public static int toInt(byte[] data) {
        int length = data.length;
        byte[] composeData;

        switch (length) {
            case 1:
                composeData = ArrayUtils.addAll(TO_SHORT, data);
                break;
            case 2:
                composeData = ArrayUtils.addAll(TO_INTEGER, data);
                break;
            default:
                throw new IllegalArgumentException("byte array lenth must be 1 or 2");
        }

        return new BigInteger(composeData).intValue();
    }

    /** 整型转byte[], 限制 [0, 65535]
     * @param data
     * @return  如 255 => {0xFF}
     */
    public static byte[] fromInt(int data) {
        byte[] res = null;
        if (data < 0 || data > 0xFFFF) {
            throw new IllegalArgumentException("int must be lower than 65535");
        } else if (data < 0xFF) {
            res = ByteBuffer.allocate(2).putShort((short)data).array();

        } else {
            res = ByteBuffer.allocate(4).putInt(data).array();
        }
        return ArrayUtils.removeAllOccurences(res, (byte)0);
    }

    /** Byte数组转为String,
     * @param data
     * @return  如{0xFF, 0x01} => "FF01"
     */
    public static String toHexString(byte[] data) {
        return hexBinaryAdapter.marshal(data);
    }

    /** String转为Byte,
     * @param data
     * @return  如 "FF01" => {0xFF, 0x01}
     */
    public static byte[] fromHexString(String data) {
        return hexBinaryAdapter.unmarshal(data.replace(" ", ""));
    }
  /** int转为十六进制String,
     * @param data
     * @return
     */
    public static String toHexString(int data) {

        return Integer.toHexString(data);
    }

    /** 十六进制转,
     * @param data
     * @return
     */
    public static int HexStringToInt(String data) {

        return Integer.parseInt(data,16);
    }

}
