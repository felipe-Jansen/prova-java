package br.com.confidencecambio.javabasico.model;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    private String[] getNameSplited() {
        return this.name.split(" ");
    }

    private String deleteEmptySpaces(String name) {
        return Arrays.stream(name.split("\\s"))
                .filter(StringUtils::hasText)
                .collect(Collectors.joining(" "));
    }

    public void setName(String name) {
        if (!StringUtils.hasText(name)) {
            throw new RuntimeException("Nome não pode ser nulo ou vazio");
        }
        this.name = deleteEmptySpaces(name);
    }

    public String getNameCapitalized() {
        return Arrays.stream(getNameSplited())
                .map(StringUtils::capitalize)
                .collect(Collectors.joining(" "));
    }

    public String getNameUppercase() {
        return Arrays.stream(getNameSplited())
                .map(String::toUpperCase)
                .collect(Collectors.joining(" "));

    }

    public String getFirstName() {
        return Arrays.stream(getNameSplited())
                .findFirst()
                .orElse(null);
    }

    public String getLastName() {
        return Arrays.stream(getNameSplited())
                .skip(1)
                .collect(Collectors.joining(" "));
    }

    public String getAbbreviatedName() {
        String[] nomeList = getNameSplited();
        StringBuilder nomeAbreviado = new StringBuilder();
        int indexOfLastName = nomeList.length - 1;

        nomeAbreviado.append(StringUtils.capitalize(nomeList[0])).append(" ");
        Arrays.stream(nomeList, 1, indexOfLastName)
                .map(nome -> (nome.length() > 2) ? nome.charAt(0) + ". " : "")
                .map(StringUtils::capitalize)
                .forEach(nomeAbreviado::append);
        nomeAbreviado.append(StringUtils.capitalize(nomeList[indexOfLastName]));

        return nomeAbreviado.toString();
    }

}
