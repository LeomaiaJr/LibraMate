package br.inatel.libramate.FileController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public abstract class GenericFileController<T> {

    public abstract String getFileName();

    public abstract String getSeparator();

    public abstract T serialize(BufferedReader br);

    public abstract void deserialize(BufferedWriter bw, T obj);

    public void add(T obj) {

        OutputStream os = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {
            os = new FileOutputStream(getFileName(), true);
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);

            bw.write(getSeparator() + "\n");
            deserialize(bw, obj);
        } catch (IOException e) {
            System.out.println("ERRO: " + e);
        } finally {

            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void writeAll(ArrayList<T> objs) {

        OutputStream os = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {
            os = new FileOutputStream(getFileName(), false);
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);

            for (T obj : objs) {
                bw.write(getSeparator() + "\n");
                deserialize(bw, obj);
            }
        } catch (IOException e) {
            System.out.println("ERRO: " + e);
        } finally {

            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<T> read() {

        ArrayList<T> foundInFile = new ArrayList<>();

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        String linhaLer;

        try {
            is = new FileInputStream(getFileName());
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            linhaLer = br.readLine();

            while (linhaLer != null) {
                if (linhaLer.contains(getSeparator())) {
                    foundInFile.add(serialize(br));
                }

                linhaLer = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                System.out.println("ERRO: " + e);
            }
        }
        return foundInFile;
    }

}
