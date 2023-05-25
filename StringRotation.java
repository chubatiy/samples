/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chubatiy.some.task;

/**
 *
 * @author chubatiy
 */
public class StringRotation {

    public String rotate(String text, int shift) {
        //if not need rotation
        if (!isNeedRotation(text, shift)) {
            return text;
        }
        //calculate shift
        shift = shift % text.length();
        //get shifted part
        return new StringBuilder(text.substring(text.length() - shift))
                //get other part
                .append(text.substring(0, text.length() - shift))
                //build new string
                .toString();
    }

    private boolean isNeedRotation(String text, int shift) {
        return null != text
                && shift > 0
                && shift != text.length();
    }
}
