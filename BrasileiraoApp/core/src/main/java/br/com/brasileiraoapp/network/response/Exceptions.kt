package br.com.brasileiraoapp.network.response

class ZeroResultException(
    override val message: String? = "Não foram encontrado resultados"
) : Exception()