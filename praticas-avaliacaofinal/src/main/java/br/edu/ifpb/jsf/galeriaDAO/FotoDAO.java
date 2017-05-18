/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.jsf.galeriaDAO;

import br.edu.ifpb.jsf.ConnectionFactory;
import br.edu.ifpb.jsf.galeriabeans.Evento;
import br.edu.ifpb.jsf.galeriabeans.Foto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asheley
 */
public class FotoDAO {
     private Connection con;
    private final String COL_ID = "id";
    private final String COL_IMAGEM = "imagem";
    private final String COL_ORDEM = "ordem";
    private final String COL_DESCRICAO = "descricao";
    private final String COL_EVENTO = "eventos_id";

    public FotoDAO() {
        con = ConnectionFactory.getConnection();
    }

    public List<Foto> listaTodas() throws SQLException {
        List<Foto> fotos = new ArrayList<Foto>();
        EventoDAO eventoDAO = new EventoDAO();
        String sql = "select * from fotos order by ordem";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Foto foto = new Foto();
            foto.setId(rs.getLong(COL_ID));
            foto.setImagem(rs.getBytes(COL_IMAGEM));
            foto.setOrdem(rs.getInt(COL_ORDEM));
            foto.setDescricao(rs.getString(COL_DESCRICAO));
            foto.setEvento(eventoDAO.buscaPorId(rs.getLong(COL_EVENTO)));
            fotos.add(foto);
        }
        return fotos;
    }
    
    public Foto buscaPorId(Long id) throws SQLException {
        Foto foto = null;
        EventoDAO eventoDAO = new EventoDAO();
        String sql = "select * from fotos where id=? order by ordem";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {  
            foto = new Foto();
            foto.setId(rs.getLong(COL_ID));
            foto.setImagem(rs.getBytes(COL_IMAGEM));
            foto.setOrdem(rs.getInt(COL_ORDEM));
            foto.setDescricao(rs.getString(COL_DESCRICAO));
            foto.setEvento(eventoDAO.buscaPorId(rs.getLong(COL_EVENTO)));            
        }
        return foto;
    }
    
    public List<Foto> buscaPorEvento(Evento evento) throws SQLException {
        List<Foto> fotos = new ArrayList<Foto>();
        EventoDAO eventoDAO = new EventoDAO();
        String sql = "select * from fotos where eventos_id=? order by ordem";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, evento.getId());
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Foto foto = new Foto();
            foto.setId(rs.getLong(COL_ID));
            foto.setImagem(rs.getBytes(COL_IMAGEM));
            foto.setOrdem(rs.getInt(COL_ORDEM));
            foto.setDescricao(rs.getString(COL_DESCRICAO)); 
            foto.setEvento(eventoDAO.buscaPorId(rs.getLong(COL_EVENTO)));
            fotos.add(foto);
        }
        return fotos;
    }
    
    public void salva(Foto foto) throws SQLException {
        String sql = "insert into fotos(imagem,ordem,eventos_id,descricao) values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setBytes(1, foto.getImagem());
        ps.setInt(2, foto.getOrdem());
        ps.setLong(3, foto.getEvento().getId());
        ps.setString(4, foto.getDescricao());
        ps.execute();
    }
}
