import context.Context;
import dao.inter.UserDaoInter;

public class main {
    public static void main(String[] args) {

        UserDaoInter dao = Context.instanceUserDao();
        System.out.println(dao.getById(4));
    }
}
