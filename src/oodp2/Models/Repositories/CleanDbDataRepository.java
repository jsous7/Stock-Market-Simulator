package oodp2.Models.Repositories;

import oodp2.Services.DataAccess.Dao;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class CleanDbDataRepository {
    public static void cleanAll() throws Exception {
        String[] tables = {"investor", "portfolio", "stock_share", "company"};
        Dao dao = new Dao();
        
        for (int i=0; i<tables.length; i++){
            dao.truncateTable(tables[i]);
        }
    }
}
