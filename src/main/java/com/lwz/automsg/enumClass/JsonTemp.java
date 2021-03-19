package com.lwz.automsg.enumClass;

import com.lwz.automsg.DTO.JsonDTO;
import org.springframework.stereotype.Component;

/**
 * @author lwz
 */
@Component
public class JsonTemp {

    /**
     * yuqueTofeishu
     * */
    public static String richText(String title,String imgKey){
        String richTextMsg = "{\"email\":\"fanlv@bytedance.com\",\"msg_type\":\"post\"," +
                "\"content\":{\"post\":{\"zh_cn\":{\"title\":\""+title+"\"," +
                "\"content\":[[{\"tag\":\"text\",\"un_escape\":true,\"text\":\"第一行&nbsp;:\"}," +
                "{\"tag\":\"a\",\"text\":\"超链接\",\"href\":\"http://www.feishu.cn\"}," +
                "{\"tag\":\"at\",\"user_id\":\"ou_18eac85d35a26f989317ad4f02e8bbbb\"}]," +
                "[{\"tag\":\"text\",\"text\":\"第二行 :\"},{\"tag\":\"text\",\"text\":\"文本测试\"}]," +
                "[{\"tag\":\"img\",\"image_key\":\""+imgKey+"\",\"width\":300,\"height\":300}]]}}}}";
        return richTextMsg;
    }
//http://8.131.64.240:3030/tapd
    /**
     * yuqueTofeishu
     **/
    public static String msgCard(JsonDTO jsonDto){
        return "{\n" +
                "   \"chat_id\": \""+jsonDto.getCharId()+"\",\n" +
                "   \"msg_type\": \"interactive\",\n" +
                "   \"update_multi\":false,\n" +
                "   \"card\": {"+
                "  \"config\": {\n" +
                "    \"wide_screen_mode\": true\n" +
                "  },\n" +
                "  \"i18n_elements\": {\n" +
                "    \"zh_cn\": [\n" +
                "      {\n" +
                "        \"tag\": \"div\",\n" +
                "        \"text\": {\n" +
                "          \"tag\": \"lark_md\",\n" +
                "          \"content\": \""+jsonDto.getDataDTO().getTitle()+"\"\n" +
                "        },\n" +
                "        \"extra\": {\n" +
                "          \"tag\": \"img\",\n" +
                "          \"img_key\": \""+jsonDto.getImageKey()+"\",\n" +
                "          \"alt\": {\n" +
                "            \"tag\": \"plain_text\",\n" +
                "            \"content\": \"图片\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"tag\": \"div\",\n" +
                "        \"text\": {\n" +
                "          \"tag\": \"lark_md\",\n" +
                "          \"content\": \"["+jsonDto.getBookDTO().getDescription()+"](https://www.yuque.com/"+jsonDto.getDataDTO().getPath()+")\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"tag\": \"hr\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"tag\": \"note\",\n" +
                "        \"elements\": [\n" +
                "          {\n" +
                "            \"tag\": \"img\",\n" +
                "            \"img_key\": \""+jsonDto.getImageKey()+"\",\n" +
                "            \"alt\": {\n" +
                "              \"tag\": \"plain_text\",\n" +
                "              \"content\": \"图片\"\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"tag\": \"plain_text\",\n" +
                "            \"content\": \""+jsonDto.getUserDTO().getName()+"\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}}";
    }


    /**
     * yuqueTofeishu
     * */
    public String createNoticeCard(JsonDTO jsonDTO){
        return "{\n\"chat_id\": \""+jsonDTO.getCharId()+"\",\n" +
                "\"msg_type\": \"interactive\",\n" +
                "\"update_multi\": false,\n" +
                "\"card\": {" +
                "  \"config\": {\n" +
                "    \"wide_screen_mode\": true\n" +
                "  },\n" +
                "  \"elements\": [\n" +
                "    {\n" +
                "      \"tag\": \"div\",\n" +
                "      \"text\": {\n" +
                "        \"tag\": \"lark_md\",\n" +
                "        \"content\": \""+jsonDTO.getUserDTO().getName()+"新创建了一篇文章\"\n" +
                "      },\n" +
                "      \"extra\": {\n" +
                "        \"tag\": \"img\",\n" +
                "        \"img_key\": \""+jsonDTO.getImageKey()+"\",\n" +
                "        \"alt\": {\n" +
                "          \"tag\": \"plain_text\",\n" +
                "          \"content\": \"图片\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"tag\": \"hr\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"tag\": \"div\",\n" +
                "      \"text\": {\n" +
                "        \"tag\": \"lark_md\",\n" +
                "        \"content\": \"["+jsonDTO.getDataDTO().getTitle()+"](https://www.yuque.com/"+jsonDTO.getDataDTO().getPath()+")\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}}}";
    }

    public String updateNoticeCard(JsonDTO jsonDTO){
        return "{\n\"chat_id\": \""+jsonDTO.getCharId()+"\",\n" +
                "\"msg_type\": \"interactive\",\n" +
                "\"update_multi\": false,\n" +
                "\"card\": {" +
                "  \"config\": {\n" +
                "    \"wide_screen_mode\": true\n" +
                "  },\n" +
                "  \"elements\": [\n" +
                "    {\n" +
                "      \"tag\": \"div\",\n" +
                "      \"text\": {\n" +
                "        \"tag\": \"lark_md\",\n" +
                "        \"content\": \"["+jsonDTO.getDataDTO().getTitle()+"](https://www.yuque.com/"+jsonDTO.getDataDTO().getPath()+")\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"tag\": \"hr\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"tag\": \"div\",\n" +
                "      \"text\": {\n" +
                "        \"tag\": \"lark_md\",\n" +
                "        \"content\": \""+jsonDTO.getDataDTO().getBodyHtml()+"\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"tag\": \"img\",\n" +
                "      \"title\": {\n" +
                "        \"tag\": \"lark_md\",\n" +
                "        \"content\": \"图片标题\"\n" +
                "      },\n" +
                "      \"img_key\": \""+jsonDTO.getImageKey()+"\",\n" +
                "      \"alt\": {\n" +
                "        \"tag\": \"plain_text\",\n" +
                "        \"content\": \"图片\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"tag\": \"hr\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"tag\": \"note\",\n" +
                "      \"elements\": [\n" +
                "        {\n" +
                "          \"tag\": \"img\",\n" +
                "          \"img_key\": \""+jsonDTO.getImageKey()+"\",\n" +
                "          \"alt\": {\n" +
                "            \"tag\": \"plain_text\",\n" +
                "            \"content\": \"图片\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"tag\": \"plain_text\",\n" +
                "          \"content\": \""+jsonDTO.getUserDTO().getName()+"\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}}}";
    }

    public String deleteNoticeCard(JsonDTO jsonDTO){
        return "{\n\"chat_id\": \""+jsonDTO.getCharId()+"\",\n" +
                "\"msg_type\": \"interactive\",\n" +
                "\"update_multi\": false,\n" +
                "\"card\": {" +
                "  \"config\": {\n" +
                "    \"wide_screen_mode\": true\n" +
                "  },\n" +
                "  \"elements\": [\n" +
                "    {\n" +
                "      \"tag\": \"div\",\n" +
                "      \"text\": {\n" +
                "        \"tag\": \"lark_md\",\n" +
                "        \"content\": \""+jsonDTO.getUserDTO().getName()+"删除了一篇文章\"\n" +
                "      },\n" +
                "      \"extra\": {\n" +
                "        \"tag\": \"img\",\n" +
                "        \"img_key\": \""+jsonDTO.getImageKey()+"\",\n" +
                "        \"alt\": {\n" +
                "          \"tag\": \"plain_text\",\n" +
                "          \"content\": \"图片\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"tag\": \"hr\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"tag\": \"div\",\n" +
                "      \"text\": {\n" +
                "        \"tag\": \"lark_md\",\n" +
                "        \"content\": \"["+jsonDTO.getDataDTO().getTitle()+"](https://www.yuque.com/"+jsonDTO.getDataDTO().getPath()+")\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}}}";
    }

    public String newCommentsNoticeCard(){
        return "";
    }

    public String updateCommentsNoticeCard(){
        return "";
    }

    public String deleteCommentsNoticeCard(){
        return "";
    }
}
