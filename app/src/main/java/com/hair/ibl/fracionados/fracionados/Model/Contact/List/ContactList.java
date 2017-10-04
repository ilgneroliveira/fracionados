package com.hair.ibl.fracionados.fracionados.Model.Contact.List;

import com.google.gson.annotations.SerializedName;

/**
 * ContactList
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class ContactList {
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
