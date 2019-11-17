package br.verumapps.aapide.manager;

public class User
{
    private String title;
    
    private String image;
    
    private String desc;
    
    public User (String name, String desc, String image)
    {
        this.title = name;
        this.desc = desc;
        this.image = image;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String name)
    {
        this.title = name;
    }

    public String getDesc ()
    {
        return desc;
    }

    public void setDesc (String name)
    {
        this.desc = name;
    }
    public String getImagePath ()
    {
        return image;
    }

    public void setImagePath (String image)
    {
        this.image = image;
    }
}
