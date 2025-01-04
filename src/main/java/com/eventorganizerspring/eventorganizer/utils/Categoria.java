package com.eventorganizerspring.eventorganizer.utils;

import lombok.Getter;

public enum Categoria {
MUSICA(1), DANCA(2), GASTRONOMIA(3), DEBATE(4);
@Getter
private Integer categoria;
 Categoria(Integer categoria) {
    this.categoria=categoria;
}
}
