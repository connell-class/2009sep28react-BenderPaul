package com.projectzero.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.projectzero.config.EnvironmentConnectionUtil;
import com.projectzero.model.Offers;

public class OffersDao implements DaoContract<Offers, Integer>{
	
	/**
	 * 
	 * The CarDao class implements the DaoContract, and allows us to implement methods between the tables within our database and the Objects in our application.
	 **/

	@Override
	public List<Offers> findAll() {
		List<Offers> offers = new LinkedList<>();
		String sqlQuery = "select * from offers";
		try(Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
			//ps.setString(1, "offers");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				offers.add(new Offers(rs.getInt(1), rs.getBigDecimal(2), rs.getBigDecimal(3), rs.getString(4), rs.getString(5)));
			}
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return offers;
	}

	@Override
	public Offers findById(Integer i) {
		Offers findOffer = null;
		String sqlQuery = "select * from offers where tag_id = ?";
		try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				findOffer = new Offers(rs.getInt(1), rs.getBigDecimal(2), rs.getBigDecimal(3), rs.getString(4), rs.getString(5));
			}
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return findOffer;
	}

	@Override
	public Offers update(Offers t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Offers create(Offers t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Integer i) {
		String sqlQuery = "delete from offers where tag_id = ?";
		try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
			ps.setInt(1, i);
			ps.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		//soldPrice.close();
		System.out.println("\nOffer deleted");
		return i;
	}
}
