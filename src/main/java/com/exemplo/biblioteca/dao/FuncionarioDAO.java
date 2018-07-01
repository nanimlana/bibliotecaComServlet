package com.exemplo.biblioteca.dao;

import com.exemplo.biblioteca.entidades.Funcionario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class FuncionarioDAO {
    @PersistenceContext(unitName = "bibliotecaPU")
    EntityManager em;

    public List<Funcionario> buscaTodosFuncionarios() {
        try {
            TypedQuery<Funcionario> q = em.createQuery("SELECT f FROM Funcionario f", Funcionario.class);
            return q.getResultList();
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    public List<Funcionario> buscaFuncionarioPorNome(String nome) {
        List<Funcionario> funcionarios = new ArrayList<>();
        for (Funcionario funcionario : buscaTodosFuncionarios()) {
            if (funcionario.getNome().toLowerCase().contains(nome.toLowerCase())) {
                funcionarios.add(funcionario);
            }
        }
        return funcionarios;
    }

    public void adicionaFuncionario(Funcionario funcionario) {
        em.persist(funcionario);
        }

    public void removeFuncionario(Funcionario funcionario) {
        em.remove(funcionario);
    }
}
