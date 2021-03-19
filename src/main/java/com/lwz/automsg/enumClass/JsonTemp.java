package com.lwz.automsg.enumClass;

/**
 * @author lwz
 */
public class JsonTemp {
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

    public static String msgCard(String chatId,String title,String imgKey,String description,String path,String remark){
        return "{\n" +
                "   \"chat_id\": \""+chatId+"\",\n" +
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
                "          \"content\": \""+title+"\"\n" +
                "        },\n" +
                "        \"extra\": {\n" +
                "          \"tag\": \"img\",\n" +
                "          \"img_key\": \""+imgKey+"\",\n" +
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
                "          \"content\": \"["+description+"](https://www.yuque.com/"+path+")\"\n" +
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
                "            \"img_key\": \""+imgKey+"\",\n" +
                "            \"alt\": {\n" +
                "              \"tag\": \"plain_text\",\n" +
                "              \"content\": \"图片\"\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"tag\": \"plain_text\",\n" +
                "            \"content\": \""+remark+"\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}}";



    }
}
