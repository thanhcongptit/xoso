/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.constant;

/**
 *
 * @author TUYENTT
 */
public class AccountRights {
    // Basic right, applied for all paths
    public final static long ADM_RIGHT_CORE_CREATE = 0x0000000000000001;
    public final static long ADM_RIGHT_CORE_DELETE = 0x0000000000000002;
    public final static long ADM_RIGHT_CORE_UPDATE = 0x0000000000000004;
    public final static long ADM_RIGHT_CORE_SELECT = 0x0000000000000008;

    // Right to access each path
    public final static long ADM_RIGHT_PATH_LOTTERY_NEWS       = 0x0000000000000010;
    public final static long ADM_RIGHT_PATH_LOTTERY_SEO 	   = 0x0000000000000020;
    public final static long ADM_RIGHT_PATH_LOTTERY_SITE    = 0x0000000000000040;
    public final static long ADM_RIGHT_PATH_LOTTERY_ACCOUNT    = 0x0000000000000080;
    
}
