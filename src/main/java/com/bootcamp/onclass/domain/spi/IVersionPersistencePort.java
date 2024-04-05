package com.bootcamp.onclass.domain.spi;

import com.bootcamp.onclass.domain.model.Version;

public interface IVersionPersistencePort {

    Version addVersion(Version version);
}
