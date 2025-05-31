package tech.radhi.portfolio;

import gg.jte.generated.precompiled.*;
import gg.jte.generated.precompiled.fragments.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.stereotype.Component;
import tech.radhi.portfolio.dto.ProjectTemplate;

import java.util.List;

@Component
public class ResourceRuntimeHints implements RuntimeHintsRegistrar {

    private static final Logger log = LoggerFactory.getLogger(ResourceRuntimeHints.class);

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {

        log.info("Registering hints for resources");

        var classes = List.of(
                JteindexGenerated.class,
                JteaboutGenerated.class,
                JtemainGenerated.class,
                JteqaitemGenerated.class,
                ProjectTemplate.class,
                Jte403Generated.class,
                JteerrorGenerated.class,
                JtestatsGenerated.class,
                JtegithubstatsGenerated.class,
                JterequeststatsGenerated.class,
                JteupptimestatsGenerated.class,
                JteskillsGenerated.class,
                JtepdfitemGenerated.class
        );

        classes.forEach(c ->
                hints.reflection().registerType(c,
                            MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                            MemberCategory.INVOKE_DECLARED_METHODS
                )
        );
        hints.resources().registerPattern("**/*.bin");
    }
}