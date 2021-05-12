
package com.darian.utils.whitelist;

import org.junit.Test;

public class StringWhiteListTest {

    @Test
    public void update() {
        StringWhiteList stringWhiteList = new StringWhiteList();
        stringWhiteList.update("aLL");
        System.out.println(stringWhiteList);
        System.out.println(stringWhiteList.isInWhiteList("xx"));

        stringWhiteList.update("xx");
        System.out.println(stringWhiteList);
        System.out.println(stringWhiteList.isInWhiteList("xx"));

        stringWhiteList.update(", , , , ");
        System.out.println(stringWhiteList);
    }
}