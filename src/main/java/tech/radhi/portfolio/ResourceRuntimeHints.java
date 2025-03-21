package tech.radhi.portfolio;

import gg.jte.generated.precompiled.JteaboutGenerated;
import gg.jte.generated.precompiled.JteindexGenerated;
import gg.jte.generated.precompiled.JtemainGenerated;
import gg.jte.generated.precompiled.JteqaitemGenerated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.stereotype.Component;

@Component
public class ResourceRuntimeHints implements RuntimeHintsRegistrar {

    private static final Logger log = LoggerFactory.getLogger(ResourceRuntimeHints.class);

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {

        log.info("Registering hints for resources");

        hints.resources()
                .registerPattern("**/*.bin");

        hints.reflection()
                .registerType(JteindexGenerated.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);
        hints.reflection()
                .registerType(JteaboutGenerated.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);
        hints.reflection()
                .registerType(JtemainGenerated.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);
        hints.reflection()
                .registerType(JteqaitemGenerated.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);
    }
}