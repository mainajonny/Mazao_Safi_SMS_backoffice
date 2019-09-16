package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Page;
import models.Mfarmer_numbers;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;
import play.libs.Json;
import play.mvc.*;
import org.apache.commons.io.FileUtils;

import views.html.Farmer.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by maina on 9/11/2019.
 */
public class CFarmers extends Controller {

    public static Result renderViewNumbers(){
        return ok(ViewNumbers.render("Farmer numbers List"));
    }

    public static Result numberslist() {

        Map<String, String[]> params = request().queryString();

        Integer iTotalRecords = Mfarmer_numbers.findPhonenumbers.findRowCount();
        String filter = params.get("sSearch")[0];
        Integer pageSize = Integer.valueOf(params.get("iDisplayLength")[0]);
        Integer page = Integer.valueOf(params.get("iDisplayStart")[0]) / pageSize;

        /**
         * Get sorting order and column
         */
        String sortBy = "Tel";
        String order = params.get("sSortDir_0")[0];

        switch (Integer.valueOf(params.get("iSortCol_0")[0])) {
            case 0:
                sortBy = "Tel";
                break;
            case 1:
                sortBy = "nid";
                break;

        }

        /**
         * Get page to show from database
         * It is important to set setFetchAhead to false, since it doesn't benefit a stateless application at all.
         */
        Page<Mfarmer_numbers> areaPage = Mfarmer_numbers.findPhonenumbers.where(
                Expr.or(
                        Expr.ilike("Tel", "%" + filter + "%"),
                        Expr.or(
                                Expr.ilike("nid", "%" + filter + "%"),
                                Expr.ilike("Tel", "%" + filter + "%")
                        )
                )
        )
                .orderBy(sortBy + " " + order + ", nid " + order)
                .findPagingList(pageSize).setFetchAhead(false)
                .getPage(page);

        Integer iTotalDisplayRecords = areaPage.getTotalRowCount();


        ObjectNode result = Json.newObject();

        result.put("sEcho", Integer.valueOf(params.get("sEcho")[0]));
        result.put("iTotalRecords", iTotalRecords);
        result.put("iTotalDisplayRecords", iTotalDisplayRecords);

        ArrayNode anc = result.putArray("aaData");

        for (Mfarmer_numbers cc : areaPage.getList()) {
            ObjectNode row = Json.newObject();
            //    System.out.println("in data table fetch: " + cc.RoomName);
            row.put("Tel", cc.Tel);
            row.put("nid", cc.nid);
            //row.put("isActive", cc.isActive);
            anc.add(row);
        }

        return ok(result);
    }

}
