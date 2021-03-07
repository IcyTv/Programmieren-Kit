package edu.kit.informatik.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Sha256 Implementation based on the pseudocode found here:
 * https://www.wikiwand.com/en/SHA-2#/Pseudocode
 * </p>
 * 
 * <p>
 * The Reason this exists is because Passwords should always be hashed in order
 * to comply with GDPR https://bit.ly/3ozaWF5. And it secures future expansion
 * (to databases etc.). Also it was a fun exercise to implement my own Sha256
 * implementation since we aren't allowed to use security libraries
 * </p>
 * 
 * @author Michael Finger
 * @version 1.0.0
 */
public abstract class Sha256 {

    /**
     * Sha256 Initialization vectors
     */
    private static final int[] K = new int[] {0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1,
        0x923f82a4, 0xab1c5ed5, 0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7,
        0xc19bf174, 0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
        0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967, 0x27b70a85,
        0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85, 0xa2bfe8a1, 0xa81a664b,
        0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070, 0x19a4c116, 0x1e376c08, 0x2748774c,
        0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3, 0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208,
        0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2 };

    /**
     * Makes it impossible to instantiate this class
     */
    private Sha256() {
    }

    /**
     * Gets the SHA256 Digest in hex
     * 
     * @param data Data to hash
     * @return Hex Digest of data
     */
    public static String getDigest(String data) {
        // More Sha256 Initialization Vectors
        int h0 = 0x6a09e667;
        int h1 = 0xbb67ae85;
        int h2 = 0x3c6ef372;
        int h3 = 0xa54ff53a;
        int h4 = 0x510e527f;
        int h5 = 0x9b05688c;
        int h6 = 0x1f83d9ab;
        int h7 = 0x5be0cd19;

        List<Byte> padded = pad(data);

        for (int i = 0; i < padded.size() / 64; i++) {
            List<Byte> chunk = padded.subList(i * 64, i * 64 + 64);

            int[] w = new int[64];

            for (int n = 0; n < chunk.size(); n += 4) {
                w[n / 4] = fromByteArray(chunk.subList(n, n + 4).toArray(new Byte[4]));
            }

            for (int n = 16; n < 64; n++) {
                int s0 = Integer.rotateRight(w[n - 15], 7) ^ Integer.rotateRight(w[n - 15], 18) ^ (w[n - 15] >>> 3);
                int s1 = Integer.rotateRight(w[n - 2], 17) ^ Integer.rotateRight(w[n - 2], 19) ^ (w[n - 2] >>> 10);

                w[n] = w[n - 16] + s0 + w[n - 7] + s1;
            }

            // Working variables
            int a = h0;
            int b = h1;
            int c = h2;
            int d = h3;
            int e = h4;
            int f = h5;
            int g = h6;
            int h = h7;

            for (int n = 0; n < 64; n++) {
                int s1 = Integer.rotateRight(e, 6) ^ Integer.rotateRight(e, 11) ^ Integer.rotateRight(e, 25);
                int ch = (e & f) ^ ((~e) & g);
                int temp1 = h + s1 + ch + K[n] + w[n];
                int s0 = Integer.rotateRight(a, 2) ^ Integer.rotateRight(a, 13) ^ Integer.rotateRight(a, 22);

                int maj = (a & b) ^ (a & c) ^ (b & c);
                int temp2 = s0 + maj;

                h = g;
                g = f;
                f = e;
                e = d + temp1;
                d = c;
                c = b;
                b = a;
                a = temp1 + temp2;
            }

            h0 += a;
            h1 += b;
            h2 += c;
            h3 += d;
            h4 += e;
            h5 += f;
            h6 += g;
            h7 += h;
        }

        return intToString(h0) + intToString(h1) + intToString(h2) + intToString(h3) + intToString(h4) + intToString(h5)
                + intToString(h6) + intToString(h7);
    }

    /**
     * Pads the Data string to Sha256 Specifications
     * 
     * @param data Data to pad
     * @return Padded data
     */
    private static List<Byte> pad(String data) {
        byte[] bytes = data.getBytes();
        // byte[] padded1 = new byte[bytes.length + 1];
        ArrayList<Byte> padded = new ArrayList<Byte>();

        for (byte b : bytes) {
            padded.add(b);
        }

        padded.add((byte) 0x80); // 10000000

        int l = bytes.length;
        while ((padded.size() + 68) % 64 != 0) { // Because padded must be multiple of 512 bit and 4 bits are added at
                                                    // the end
            padded.add((byte) 0);
        }

        for (byte c : toBytes(l * 8)) {
            padded.add(c);
        }

        return padded;
    }

    /**
     * Gets hex string representation of an Integer and pads it to length 8
     * 
     * @param i Integer to convert
     * @return hex representation of i
     */
    private static String intToString(int i) {
        String nonPadded = Integer.toHexString(i);

        return ("0".repeat(8 - nonPadded.length()) + nonPadded);
    }

    /**
     * Convert i to its big endian byte representation
     * 
     * @param i 32-bit Integer to convert
     * @return ByteArray Representation of the Integer
     */
    private static byte[] toBytes(int i) {
        byte[] result = new byte[4];

        result[0] = (byte) (i >> 24);
        result[1] = (byte) (i >> 16);
        result[2] = (byte) (i >> 8);
        result[3] = (byte) (i /* >> 0 */);

        return result;
    }

    /**
     * Convert big endian byte array to integer
     * 
     * @param bytes ByteArray to turn to integer
     * @return 32-bit representation of Integer
     */
    private static int fromByteArray(Byte[] bytes) {
        return bytes[0] << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
    }
}
