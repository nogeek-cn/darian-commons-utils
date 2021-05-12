package com.darian.utils.whitelist;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class StringWhiteList {

    /**
     * ALL字符
     */
    private static final String ALL = "ALL";

    /**
     * 白名单
     */
    private Set<String> whiteListSet = Collections.emptySet();

    /**
     * 全量快捷校验
     */
    private boolean isAll = false;

    private String COMMA = ",";

    /**
     * 更新白名单配置
     *
     * @param newWhiteList 新白名单
     */
    public void update(String newWhiteList) {
        if (Objects.isNull(newWhiteList) || "".equals(newWhiteList)) {
            isAll = false;
            whiteListSet = Collections.emptySet();
            return;
        }
        isAll = ALL.equalsIgnoreCase(newWhiteList);

        whiteListSet = Arrays.stream(newWhiteList.split(COMMA))
                .map(str -> str.trim())
                .filter(str -> !"".equals(str))
                .collect(Collectors.toSet());
    }

    /**
     * 判断是否在白名单内
     *
     * @param originalString 字符串
     * @return boolean 是否在白名单内
     */
    public boolean isInWhiteList(String originalString) {
        if (Objects.isNull(originalString) || "".equals(originalString)) {
            return false;
        }
        if (isAll) {
            return true;
        }
        return whiteListSet.contains(originalString);
    }

    @Override
    public String toString() {
        return "StringWhiteList [" + "whiteListSet=" + whiteListSet + ",isAll=" + isAll + ']';
    }
}