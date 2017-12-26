package com.hair.ibl.fracionados.fracionados.Model.Contact.Show;

import com.google.gson.annotations.SerializedName;

/**
 * BlogData
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class ContactData {
    @SerializedName("form")
    private Form form = new Form();

    /**
     * @return The form
     */
    public Form getForm() {
        return form;
    }

    /**
     * @param form The form
     */
    public void setForm(Form form) {
        this.form = form;
    }
}
