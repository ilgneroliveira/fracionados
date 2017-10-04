package com.hair.ibl.fracionados.fracionados.Util;

import com.hair.ibl.fracionados.fracionados.Model.Contact.List.Form;

import java.util.ArrayList;

/**
 * ExtractHTMLUtil
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class ExtractHTMLUtil {
    public String replaceAcutesHTML(String str) {

        str = str.replaceAll("&aacute;", "á");
        str = str.replaceAll("&Aacute;", "Á");
        str = str.replaceAll("&atilde;", "ã");
        str = str.replaceAll("&Atilde;", "Ã");
        str = str.replaceAll("&acirc;", "â");
        str = str.replaceAll("&Acirc;", "Â");
        str = str.replaceAll("&agrave;", "à");
        str = str.replaceAll("&Agrave;", "À");
        str = str.replaceAll("&eacute;", "é");
        str = str.replaceAll("&Eacute;", "É");
        str = str.replaceAll("&ecirc;", "ê");
        str = str.replaceAll("&Ecirc;", "Ê");
        str = str.replaceAll("&iacute;", "í");
        str = str.replaceAll("&Iacute;", "Í");
        str = str.replaceAll("&oacute;", "ó");
        str = str.replaceAll("&Oacute;", "Ó");
        str = str.replaceAll("&otilde;", "õ");
        str = str.replaceAll("&Otilde;", "Õ");
        str = str.replaceAll("&ocirc;", "ô");
        str = str.replaceAll("&Ocirc;", "Ô");
        str = str.replaceAll("&uacute;", "ú");
        str = str.replaceAll("&Uacute;", "Ú");
        str = str.replaceAll("&ccedil;", "ç");
        str = str.replaceAll("&Ccedil;", "Ç");
        str = str.replaceAll("&Ccedil;", "Ç");
        str = str.replaceAll("&amp;", "&");
        str = str.replaceAll("&quot;", "\"");
        str = str.replaceAll("&rsquo;", "'");
        str = str.replaceAll("&nbsp;", " ");

        return str;
    }

    public ArrayList<Form> splitForms(String str) {
        ArrayList<Form> forms = new ArrayList<>();
        for (String form: str.split("\n")) {
            forms.add(new Form(form.split("=")));
        }

        return forms;
    }
}
