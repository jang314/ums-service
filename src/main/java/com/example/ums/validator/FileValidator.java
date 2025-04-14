package com.example.ums.validator;

import com.example.ums.service.template.ItemDto;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


// 파일 크기
@Getter
public class FileValidator extends ItemAdditionalValidator {
    private String[] ext;
    private int maxSize;

    public FileValidator(TypeValidator validator, String... ext) {
        super(validator);
        this.ext = ext;
    }

    public FileValidator(TypeValidator validator, int maxSize, String... ext) {
        super(validator);
        this.ext = ext;
        this.maxSize = maxSize;
    }

    private boolean existsFile(String file) {
        File f = new File(file);
        if(!f.exists()) {
            throw new IllegalArgumentException("File Not Exists");
        }
        return true;
    }

    @Override
    public JsonNode validate(JsonNode items) {
//        List<ItemDto> list = super.validate(items);
//        return list.stream()
//                .filter(item -> existsFile(item.value()))
//                .filter(item -> isValidExt(item.value()))
//                .collect(Collectors.toList());
        return null;
    }

    private boolean isValidExt(String filePath) {
        String[] strs = filePath.split("\\.");
        if(strs.length != 2) {
            throw new IllegalArgumentException("file path is not valid.");
        }
        return Arrays.stream(ext)
                .filter(x -> x.equals(strs[1]))
                .findFirst()
                .map(r -> true)
                .orElseThrow(() -> new IllegalArgumentException("file ext is not valid."));
    }
}
