package com.rubenskj.portfolio.util;

import com.rubenskj.portfolio.dto.ProjectDTO;
import com.rubenskj.portfolio.model.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParserUtil {

    private ParserUtil() {
    }


    public static List<ProjectDTO> parseProjectToDTO(List<Project> allProjectFromPerson) {
        if (allProjectFromPerson == null || allProjectFromPerson.isEmpty()) {
            return new ArrayList<>();
        }

        return allProjectFromPerson.stream().map(ProjectDTO::of).collect(Collectors.toList());
    }
}
