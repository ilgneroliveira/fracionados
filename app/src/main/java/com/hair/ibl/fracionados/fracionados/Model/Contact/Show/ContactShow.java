package com.hair.ibl.fracionados.fracionados.Model.Contact.Show;

import com.google.gson.annotations.SerializedName;

/**
 * PostShow
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class ContactShow {
    @SerializedName("data")
    private ContactData data = new ContactData();

    /**
     * @return The data
     */
    public ContactData getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(ContactData data) {
        this.data = data;
    }
}
