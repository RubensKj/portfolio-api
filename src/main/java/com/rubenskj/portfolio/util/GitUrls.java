package com.rubenskj.portfolio.util;

import com.rubenskj.portfolio.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitUrls {

    private static final List<GitProvider> providers = new ArrayList<>();

    private GitUrls() {
        this.createProvider("GITHUB", "https://api.github.com/repos/%s/%s");
    }

    private void createProvider(String providerName, String urlApi) {
        providers.add(new GitProvider(providerName, urlApi));
    }

    public static GitProvider getByNameOfProvider(String nameProvider) {
        return providers.stream().filter(gitProvider -> gitProvider.getNameProvider().equalsIgnoreCase(nameProvider)).findFirst().orElseThrow(() -> new NotFoundException("Provider not found with this name"));
    }

    public class GitProvider {
        private String nameProvider;
        private String urlToBeFormatted;

        public GitProvider() {
        }

        public GitProvider(String nameProvider, String urlToBeFormatted) {
            this.nameProvider = nameProvider;
            this.urlToBeFormatted = urlToBeFormatted;
        }

        public String getNameProvider() {
            return nameProvider;
        }

        public String getUrlToBeFormatted() {
            return urlToBeFormatted;
        }
    }
}
