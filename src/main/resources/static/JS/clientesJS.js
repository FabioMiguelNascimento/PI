   $(document).ready(function() {
        $("#btn-cadastrar").click(function(e) {
            e.preventDefault();
            $.ajax({
                type: "POST",
                url: "/clicad",
                data: $("#cad").serialize(),
                success: function(data) {
                    // Atualizar a tabela com os dados retornados da chamada AJAX
                    $("#tabela-clientes").load("/clientes #tabela-clientes");
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log("Erro ao cadastrar cliente: " + errorThrown);
                }
            });
        });
    });