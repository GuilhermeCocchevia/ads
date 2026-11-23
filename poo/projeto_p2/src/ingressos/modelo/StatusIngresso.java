package ingressos.modelo;

/** Situação de um ingresso ao longo do uso. */
public enum StatusIngresso {
    VENDIDO,     // acabou de ser vendido, ainda não entrou
    UTILIZADO,   // passou pela catraca (baixa na portaria)
    CANCELADO
}
