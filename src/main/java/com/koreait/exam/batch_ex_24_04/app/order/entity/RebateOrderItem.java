import com.koreait.exam.batch_ex_24_04.app.base.entity.BaseEntity;
import com.koreait.exam.batch_ex_24_04.app.order.entity.Order;
import com.koreait.exam.batch_ex_24_04.app.order.entity.OrderItem;
import com.koreait.exam.batch_ex_24_04.app.product.entity.ProductOption;
import lombok.ToString;

import javax.persistence.*;

public class RebateOrderItem extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private OrderItem orderItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ProductOption productOption;

    private int quantity;

    //가격
    private int price; // 권장 판매가
    private int salePrice; // 실제 판매가
    private int wholesalePrice; // 도매가
    private int pgFee; // 결제대행사 수수료
    private int payPrice; // 결제금액
    private int refundPrice; // 환불금액
    private int refundQuantity; // 환불 한 갯수
    private boolean isPaid;  // 결제 여부