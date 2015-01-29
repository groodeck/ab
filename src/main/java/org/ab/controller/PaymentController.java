package org.ab.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ab.model.InvoicePaymentModel;
import org.ab.model.PaymentModel;
import org.ab.model.SubscriberModel;
import org.ab.model.dictionary.SelectValueService;
import org.ab.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentsService paymentsService;

	@Autowired
	private SelectValueService selectValuesService;

	private SubscriberModel getSubscriber(final HttpSession session) {
		return (SubscriberModel)session.getAttribute("subscriber");
	}

	@RequestMapping("/edit/{paymentId}")
	public String handleEditPackage(@PathVariable final int paymentId, final Model model) {
		final PaymentModel payment = this.paymentsService.getPayment(paymentId);
		if(payment == null){
			model.addAttribute("uiMessage", "Nie mo¿na pobraæ szczegó³ów wp³aty");
			return "payments";
		} else {
			model.addAttribute("payment", payment);
			return "payment";
		}
	}

	@RequestMapping("/new")
	public String handleInitEntry(final Model model,  final HttpServletRequest request) {
		final SubscriberModel subscriber = getSubscriber(request.getSession());
		if(subscriber == null){
			model.addAttribute("uiMessage", "Nie wybrano abonenta");
			return "payments";
		}
		final PaymentModel payment = new PaymentModel();
		payment.setSubscriber(subscriber);
		final List<InvoicePaymentModel> invoicesToPay =
				this.paymentsService.getUnpaidInvoices(subscriber.getSubscriberId());
		payment.getInvoices().addAll(invoicesToPay);
		model.addAttribute("payment", payment);
		return "payment";
	}

	@RequestMapping("/save")
	public String handleSaveAction(final PaymentModel payment, final Model model) {
		System.out.println("saving payment " + payment);
		this.paymentsService.save(payment, null /*chyba user nie bedzie potrzebny*/);
		model.addAttribute("payment", payment);
		model.addAttribute("uiMessage", "Zapisano pakiet");
		return "payment";
	}
}