package com.author.base;

/**
 * Created by Administrator on 2016/8/8.
 */

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;

public class HTMLCoder {
    private StringBuffer htmlCode;
    private String type = "text/rtf";

    public HTMLCoder() {
    }

    public HTMLCoder(String code) {
        this.htmlCode.append(code);
    }

    public HTMLCoder(Reader rtf) throws IOException {
        JEditorPane p = new JEditorPane();
        p.setContentType(this.type);
        EditorKit kitRtf = p.getEditorKitForContentType(this.type);

        try {
            kitRtf.read(rtf, p.getDocument(), 0);
            kitRtf = null;
            EditorKit e = p.getEditorKitForContentType("text/html");
            StringWriter writer = new StringWriter();
            e.write(writer, p.getDocument(), 0, p.getDocument().getLength());
            this.htmlCode.append(writer.toString());
        } catch (BadLocationException var6) {
            var6.printStackTrace();
        }

    }
}