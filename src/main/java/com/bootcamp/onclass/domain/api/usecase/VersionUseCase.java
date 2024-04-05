package com.bootcamp.onclass.domain.api.usecase;

import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.domain.api.IVersionServicePort;
import com.bootcamp.onclass.domain.exception.ValidateDateException;
import com.bootcamp.onclass.domain.model.Version;
import com.bootcamp.onclass.domain.spi.IVersionPersistencePort;


public class VersionUseCase implements IVersionServicePort {
    private final IVersionPersistencePort versionPersistencePort;

    public VersionUseCase(IVersionPersistencePort versionPersistencePort) {
        this.versionPersistencePort = versionPersistencePort;
    }

    @Override
    public Version addVersion(Version version) {

        if (!version.getFinalDate().isAfter(version.getInitialDate())) {
            throw new ValidateDateException(Constants.VALIDATE_DATE_RANGE_EXCEPTION_MESSAGE);
        }

        return versionPersistencePort.addVersion(version);
    }

}
