/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginTCP.DaoChuoi;

import java.lang.String;

/**
 *
 * @author thuc
 */
public class ReverseString {

    private String _string;
// khoi tao khong tham so

    public ReverseString() {
    }
// khoi tao co tham so

    public ReverseString(String _string) {
        this._string = _string;
    }

    public String get_string() {
        return _string;
    }

    public void set_string(String _string) {
        this._string = _string;
    }
//phuong thuc dao nguoc chuoi ki tu cua lop nay

    public void reverse() {
        String tmp = "";
        for (int i = _string.length() - 1; i >= 0; i--) {
            tmp += _string.substring(i, i + 1);
        }
        this._string = tmp;
    }
}
