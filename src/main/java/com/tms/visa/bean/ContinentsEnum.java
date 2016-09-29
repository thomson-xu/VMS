package com.tms.visa.bean;

/**
 * Created by Administrator on 2016/7/3.
 */
public enum ContinentsEnum {
    ASIA("����", 1), EUROPE("ŷ��", 2), NORTHAMERICA("������", 3), SOUTHAMERICA("������", 4), AFRICA("����", 5), OCEANIA("������", 6), ANTARCTICA("�ϼ���", 7);

    // ��Ա����
    private String name;
    private int index;

    // ���췽��
    private ContinentsEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // ��ͨ����
    public static String getName(int index) {
        for (ContinentsEnum c : ContinentsEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public static int getValue(String name) {
        for (ContinentsEnum c : ContinentsEnum.values()) {
            if (c.getName().equals(name)) {
                return c.index;
            }
        }
        return 0;
    }
    // get set ����
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
