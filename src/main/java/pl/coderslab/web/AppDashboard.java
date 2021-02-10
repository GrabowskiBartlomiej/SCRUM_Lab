package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "AppDashboard",urlPatterns = "/app")
public class AppDashboard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        int id = admin.getId();

        RecipeDao r = new RecipeDao();
<<<<<<< HEAD
        int quantityOfUsersRecipes = r.quantityOfAdminRecipes(id);
        session.setAttribute("quantityOfUsersRecipes",quantityOfUsersRecipes);

        PlanDao pd = new PlanDao();
        Plan lastPlan = pd.getTheLastPlan();
        session.setAttribute("lastPlan",lastPlan);
        int quantityOfUsersPlans = pd.quantityOfAdminRecipes(id);
        session.setAttribute("quantityOfUsersPlans",quantityOfUsersPlans);
=======
        List<Recipe> adminRecipes = r.allAdminRecipes(id);
        session.setAttribute("adminRecipes",adminRecipes);

        PlanDao pd = new PlanDao();
        List<Plan> adminPlans = pd.allAdminPlans(id);
        session.setAttribute("adminPlans",adminPlans);
        Collections.sort(adminPlans,Collections.reverseOrder());
        int planId = adminPlans.get(0).getId();
>>>>>>> 6554cdbdc9c6a209f3bf3565c6cc7bd81c27efd0

        Map<String,List<RecipePlan>> displayList = new HashMap<>();
        RecipePlanDao rpd = new RecipePlanDao();
        for(int i = 1; i < 8; i++){
<<<<<<< HEAD
            List<RecipePlan> rp = rpd.findAllRecipesByDay(i,lastPlan.getId());
=======
            List<RecipePlan> rp = rpd.findAllRecipesByDay(i,planId);
>>>>>>> 6554cdbdc9c6a209f3bf3565c6cc7bd81c27efd0
            if(rp==null || rp.isEmpty()){

            }else {
                displayList.put(rpd.getDayName(i),rp);
            }
        }

        session.setAttribute("displayList", displayList);
<<<<<<< HEAD
=======

>>>>>>> 6554cdbdc9c6a209f3bf3565c6cc7bd81c27efd0
        getServletContext().getRequestDispatcher("/appDashboard.jsp").forward(request,response);
    }
}
