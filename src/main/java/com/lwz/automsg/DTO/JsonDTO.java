package com.lwz.automsg.DTO;

/**
 * @author 吕文哲
 */
public class JsonDTO {
    private BookDTO bookDTO;
    private DataDTO dataDTO;
    private RootDTO rootDTO;
    private UserDTO userDTO;
    private String charId;
    private String imageKey;

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }

    public DataDTO getDataDTO() {
        return dataDTO;
    }

    public void setDataDTO(DataDTO dataDTO) {
        this.dataDTO = dataDTO;
    }

    public RootDTO getRootDTO() {
        return rootDTO;
    }

    public void setRootDTO(RootDTO rootDTO) {
        this.rootDTO = rootDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getCharId(){
        return charId;
    }

    public void setCharId(String charId){
        this.charId = charId;
    }


}
