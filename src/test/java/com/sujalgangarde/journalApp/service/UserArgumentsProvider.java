package com.sujalgangarde.journalApp.service;

import com.sujalgangarde.journalApp.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
//                Always provide new instances of User to avoid state issues.
                Arguments.of(User.builder().username("sdf").password("7s89d").build()),
                Arguments.of(User.builder().username("jssgkdl").password("10s1d112").build())
        );
    }
}
