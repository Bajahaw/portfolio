package tech.radhi.portfolio;

import gg.jte.generated.precompiled.JteaboutGenerated;
import gg.jte.generated.precompiled.JteindexGenerated;
import gg.jte.generated.precompiled.JtemainGenerated;
import gg.jte.generated.precompiled.JteqaitemGenerated;
import gg.jte.generated.precompiled.fragments.Jte403Generated;
import gg.jte.generated.precompiled.fragments.Jte404Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.stereotype.Component;
import tech.radhi.portfolio.content.ProjectTemplate;

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
                Jte404Generated.class
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