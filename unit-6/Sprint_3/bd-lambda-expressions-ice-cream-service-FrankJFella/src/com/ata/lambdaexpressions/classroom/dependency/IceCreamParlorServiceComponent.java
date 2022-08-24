package com.ata.lambdaexpressions.classroom.dependency;

import com.ata.lambdaexpressions.classroom.IceCreamParlorService;

import dagger.Component;

@Component
public interface IceCreamParlorServiceComponent {
    IceCreamParlorService provideIceCreamParlorService();
}
