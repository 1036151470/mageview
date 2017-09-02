package com.helloblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Controller
public class ForController {

    private String fileurl = null;
    private int numadd = 0;
    private InputStream inputStreammain = null;
    private ZipFile zipFile = null;

    @GetMapping("/forexit")
    public String forexit(){
        System.exit(0);
        return "index";
    }

    @RequestMapping("/openfile")
    public String openfile(String s) {
        inputStreammain = null;
        fileurl = s;
        numadd = 0;
        return "redirect:/next";
    }

    @RequestMapping("/skipnum")
    public String skipnum(String skipnum){

        numadd = Integer.valueOf(skipnum);

        System.out.println(numadd);
        System.out.println(skipnum);
        return "redirect:/next";
    }

    @RequestMapping("/image")
    public void image(HttpServletResponse response) throws IOException {
        if (fileurl != null) {
            response.setContentType("image/jpeg");
            ServletOutputStream out;
            out = response.getOutputStream();

            BufferedInputStream bin = new BufferedInputStream(inputStreammain);

            BufferedOutputStream bout = new BufferedOutputStream(out);
            int ch = 0;
            while ((ch = bin.read()) != -1) {
                bout.write(ch);
            }
            bin.close();
            bout.close();
            out.close();
            zipFile.close();
        }
    }

    @RequestMapping("/last")
    public String last(Map<String, Object> map) {
        if(fileurl == null){
            return "redirect:/";
        }
        numadd--;
        try {
            zipFile = new ZipFile(fileurl);
            Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zipFile.entries();
            ZipEntry ze;
            int num = 0;
            while (entries.hasMoreElements()) {
                num++;
                ze = entries.nextElement();
                if (num == numadd) {
                    inputStreammain = zipFile.getInputStream(ze);
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("numadd", numadd);
        map.put("fileurl", fileurl);
        return "index";
    }


    @RequestMapping("/next")
    public String next(Map<String, Object> map) {
        if(fileurl == null){
            return "redirect:/";
        }
        try {
            zipFile = new ZipFile(fileurl);
            Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zipFile.entries();
            ZipEntry ze;
            int num = 0;
            while (entries.hasMoreElements()) {

                ze = entries.nextElement();
                inputStreammain = zipFile.getInputStream(ze);
                if (num == numadd) {
                    break;
                }
                num++;
            }
            if (numadd > num) {
                zipFile.close();
                numadd = 0;
                return "redirect:/";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        numadd++;
        map.put("numadd", numadd);
        map.put("fileurl", fileurl);
        return "index";
    }

    @RequestMapping("/")
    public String index(Map<String, Object> map) {
        map.put("numadd", numadd);
        map.put("fileurl", fileurl);
        return "index";
    }
}
