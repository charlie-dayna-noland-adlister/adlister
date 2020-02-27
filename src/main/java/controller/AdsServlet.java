package controller;

import model.AdImp;
import model.Ads;
import model.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ads")
public class AdsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ads adsDao = DaoFactory.getAdsDao();
        List<AdImp> adImps = adsDao.all();
        request.setAttribute("ads", adImps);
        request.getRequestDispatcher("/ads/index.jsp").forward(request, response);
    }
}
