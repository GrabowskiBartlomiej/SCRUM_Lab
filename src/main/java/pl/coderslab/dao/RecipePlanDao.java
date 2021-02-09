package pl.coderslab.dao;

import pl.coderslab.model.Plan;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipePlanDao {
    private final static String GET_RECIPES_BY_DAY_AND_PLAN = "select d.display_order ,d.name ,rp.meal_name ,r.name,r.id\n" +
            "from  recipe r\n" +
            "inner join recipe_plan rp on r.id = rp.recipe_id\n" +
            "inner join day_name d on rp.day_name_id = d.id\n" +
            "where rp.plan_id = ? AND rp.day_name_id = ?\n"+
            "order by rp.display_order";
    private final static String GET_DAY_NAME_BY_ID = "select name from day_name where id = ?";

    public static List<RecipePlan> findAllRecipesByDay(int i,int planId){
        List<RecipePlan> recpePlanList = new ArrayList<>();
        try(Connection cone = DbUtil.getConnection();){
            PreparedStatement pre = cone.prepareStatement(GET_RECIPES_BY_DAY_AND_PLAN);
            pre.setInt(1,planId);
            pre.setInt(2,i);
            ResultSet resultSet = pre.executeQuery();
            while(resultSet.next()){
                RecipePlan recipePlan = new RecipePlan();
                recipePlan.setDayId(resultSet.getInt("d.display_order"));
                recipePlan.setDayName(resultSet.getString("d.name"));
                recipePlan.setMealName(resultSet.getString("rp.meal_name"));
                recipePlan.setRecipeName(resultSet.getString("r.name"));
                recipePlan.setRecipeId(resultSet.getInt("r.id"));
                recpePlanList.add(recipePlan);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return recpePlanList;
    }

    public static String getDayName(int id){
        String dayName = "";
        try(Connection cone = DbUtil.getConnection();){
            PreparedStatement pre = cone.prepareStatement(GET_DAY_NAME_BY_ID);
            pre.setInt(1,id);
            ResultSet resultSet = pre.executeQuery();
            while(resultSet.next()){
                dayName = resultSet.getString("name");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return dayName;
    }
}
