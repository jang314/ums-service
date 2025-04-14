package com.example.ums.service.template.kakao;

import com.example.ums.dto.KakaoTemplate;
import com.example.ums.service.template.ItemDto;
import com.example.ums.util.JsonUtil;

import java.util.List;
import java.util.stream.Stream;

public record KakaoTemplateDto (
        String type,
        String templateCode,
        String kakaoSenderKey,
        ItemDto... items) {

//    KakaoTemplateDto(String type, String templateCode, String kakaoSenderKey,
//                     Highlight highlight,
//                     Image image,
//                     List<Button> buttons,
//                     List<Item> itemList) {
//        this(type, templateCode, kakaoSenderKey,
//                Stream.concat(
//                        Stream.of(
//                                new ItemDto("highlight", JsonUtil.toJson(highlight)),
//                                new ItemDto("image", JsonUtil.toJson(image))
//                        ),
//                        Stream.concat(
//                                buttons.stream().map(btn -> new ItemDto("button", JsonUtil.toJson(btn))),
//                                itemList.stream().map(item -> new ItemDto("item", JsonUtil.toJson(item)))
//                        )
//                ).toArray(ItemDto[]::new));
//    }
//    record Button(String type, String label, String url, String scheme) {}
//    record Item(String title, String description, String link){ }
//    record Highlight(String title, String subTitle) { }
//    record Image (String url, String link, int width, int height){}
}
