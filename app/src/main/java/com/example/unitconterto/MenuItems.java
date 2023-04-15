package com.example.unitconterto;

public class MenuItems
{
    private String optionName;
    private int imgId;

    public MenuItems(String optionName, int imgId)
    {
        this.optionName = optionName;
        this.imgId = imgId;
    }

    public String getOptionName()
    {
        return optionName;
    }
    public void setOptionName(String optionName)
    {
        this.optionName = optionName;
    }
    public int getImgId()
    {
        return imgId;
    }

    public void setImgId(int imgId)
    {
        this.imgId = imgId;
    }
}
